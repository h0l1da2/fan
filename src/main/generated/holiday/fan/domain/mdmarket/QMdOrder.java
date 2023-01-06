package holiday.fan.domain.mdmarket;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMdOrder is a Querydsl query type for MdOrder
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMdOrder extends EntityPathBase<MdOrder> {

    private static final long serialVersionUID = -10531083L;

    public static ConstructorExpression<MdOrder> create(Expression<Long> id, Expression<Integer> quantity, Expression<Integer> price, Expression<? extends Market> market, Expression<? extends Delivery> delivery, Expression<? extends Order> order) {
        return Projections.constructor(MdOrder.class, new Class<?>[]{long.class, int.class, int.class, Market.class, Delivery.class, Order.class}, id, quantity, price, market, delivery, order);
    }

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMdOrder mdOrder = new QMdOrder("mdOrder");

    public final QDelivery delivery;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMarket market;

    public final QOrder order;

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public QMdOrder(String variable) {
        this(MdOrder.class, forVariable(variable), INITS);
    }

    public QMdOrder(Path<? extends MdOrder> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMdOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMdOrder(PathMetadata metadata, PathInits inits) {
        this(MdOrder.class, metadata, inits);
    }

    public QMdOrder(Class<? extends MdOrder> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.delivery = inits.isInitialized("delivery") ? new QDelivery(forProperty("delivery"), inits.get("delivery")) : null;
        this.market = inits.isInitialized("market") ? new QMarket(forProperty("market"), inits.get("market")) : null;
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

