package holiday.fan.domain.mdmarket;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMarketRemover is a Querydsl query type for MarketRemover
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMarketRemover extends EntityPathBase<MarketRemover> {

    private static final long serialVersionUID = -655537904L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMarketRemover marketRemover = new QMarketRemover("marketRemover");

    public final holiday.fan.domain.members.QAdmin admin;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final holiday.fan.domain.members.QMember member;

    public final DateTimePath<java.time.LocalDateTime> removeDate = createDateTime("removeDate", java.time.LocalDateTime.class);

    public QMarketRemover(String variable) {
        this(MarketRemover.class, forVariable(variable), INITS);
    }

    public QMarketRemover(Path<? extends MarketRemover> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMarketRemover(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMarketRemover(PathMetadata metadata, PathInits inits) {
        this(MarketRemover.class, metadata, inits);
    }

    public QMarketRemover(Class<? extends MarketRemover> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new holiday.fan.domain.members.QAdmin(forProperty("admin"), inits.get("admin")) : null;
        this.member = inits.isInitialized("member") ? new holiday.fan.domain.members.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

