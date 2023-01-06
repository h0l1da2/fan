package holiday.fan.domain.mdmarket;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderRemover is a Querydsl query type for OrderRemover
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderRemover extends EntityPathBase<OrderRemover> {

    private static final long serialVersionUID = -406469374L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderRemover orderRemover = new QOrderRemover("orderRemover");

    public final holiday.fan.domain.members.QAdmin admin;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final holiday.fan.domain.members.QMember member;

    public final DateTimePath<java.time.LocalDateTime> removeDate = createDateTime("removeDate", java.time.LocalDateTime.class);

    public QOrderRemover(String variable) {
        this(OrderRemover.class, forVariable(variable), INITS);
    }

    public QOrderRemover(Path<? extends OrderRemover> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderRemover(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderRemover(PathMetadata metadata, PathInits inits) {
        this(OrderRemover.class, metadata, inits);
    }

    public QOrderRemover(Class<? extends OrderRemover> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new holiday.fan.domain.members.QAdmin(forProperty("admin"), inits.get("admin")) : null;
        this.member = inits.isInitialized("member") ? new holiday.fan.domain.members.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

