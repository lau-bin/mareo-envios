package com.lautarocutri.dev.mareoenvios.shipping.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.lautarocutri.dev.mareoenvios.shipping.repository.dto.ProductInfoDTO;
import com.lautarocutri.dev.mareoenvios.shipping.repository.dto.QProductInfoDTO;
import com.lautarocutri.dev.mareoenvios.shipping.repository.dto.QShippingInfoDTO;
import com.lautarocutri.dev.mareoenvios.shipping.repository.dto.ShippingInfoDTO;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import static com.lautarocutri.dev.mareoenvios.product.entity.QProduct.product;
import static com.lautarocutri.dev.mareoenvios.shipping.entity.QShipping.shipping;
import static com.lautarocutri.dev.mareoenvios.shipping.entity.QShippingItem.shippingItem;
import static com.lautarocutri.dev.mareoenvios.shipping.entity.QShippingItemAudited.shippingItemAudited;

@Repository
public class CustomShippingRepositoryImpl implements CustomShippingRepository {

	private final JPAQueryFactory queryFactory;

	public CustomShippingRepositoryImpl(EntityManager entityManager) {
		this.queryFactory = new JPAQueryFactory(entityManager);
	}

	@Override
	public List<ShippingInfoDTO> findShippingInfoByCustomerId(Integer customerId) {
		return queryFactory
				.select(new QShippingInfoDTO(
						shipping.id,
						shipping.customerId,
						shipping.state.stringValue(),
						shipping.sendDate,
						shipping.arriveDate,
						shipping.priority,
						shippingItem.productCount,
						product.description,
						product.weight
				))
				.from(shippingItem)
				.where(shipping.customerId.eq(customerId))
				.join(shipping).on(shipping.id.eq(shippingItem.shippingId))
				.join(product).on(product.id.eq(shippingItem.productId))
				.fetch();
	}

	@Override
	public List<ProductInfoDTO> getMostPopularProductsByOrderedProductQuantity(){
		NumberExpression<Integer> activeItemsCount = shippingItem.productCount.sum();
		NumberExpression<Integer> deletedItemsCount = shippingItemAudited.productCount.sum();

		return queryFactory
				.select(new QProductInfoDTO(
						activeItemsCount.add(deletedItemsCount), // Combine sums from both tables
						product.description
				))
				.from(product)
				.leftJoin(shippingItem).on(product.id.eq(shippingItem.productId))
				.leftJoin(shippingItemAudited).on(product.id.eq(shippingItemAudited.productId))
				.where(shippingItemAudited.userDeletionId.isNotNull())
				.groupBy(product.id)
				.orderBy(activeItemsCount.add(deletedItemsCount).desc()) // Order by combined total count
				.limit(3) // Get top 3 products
				.fetch();
	}
}
