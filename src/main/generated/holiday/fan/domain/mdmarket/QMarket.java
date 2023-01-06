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
 * QMarket is a Querydsl query type for Market
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMarket extends EntityPathBase<Market> {

    private static final long serialVersionUID = 1106304382L;

    public static ConstructorExpression<Market> create(Expression<Long> id, Expression<String> title, Expression<String> content, Expression<MarketStatus> status, Expression<? extends Md> md, Expression<? extends holiday.fan.domain.members.Member> member) {
        return Projections.constructor(Market.class, new Class<?>[]{long.class, String.class, String.class, MarketStatus.class, Md.class, holiday.fan.domain.members.Member.class}, id, title, content, status, md, member);
    }

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMarket market = new QMarket("market");

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMarketRemover marketRemover;

    public final QMd md;

    public final holiday.fan.domain.members.QMember member;

    public final EnumPath<MarketStatus> status = createEnum("status", MarketStatus.class);

    public final StringPath title = createString("title");

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> uploadDate = createDateTime("uploadDate", java.time.LocalDateTime.class);

    public QMarket(String variable) {
        this(Market.class, forVariable(variable), INITS);
    }

    public QMarket(Path<? extends Market> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMarket(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMarket(PathMetadata metadata, PathInits inits) {
        this(Market.class, metadata, inits);
    }

    public QMarket(Class<? extends Market> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.marketRemover = inits.isInitialized("marketRemover") ? new QMarketRemover(forProperty("marketRemover"), inits.get("marketRemover")) : null;
        this.md = inits.isInitialized("md") ? new QMd(forProperty("md")) : null;
        this.member = inits.isInitialized("member") ? new holiday.fan.domain.members.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

