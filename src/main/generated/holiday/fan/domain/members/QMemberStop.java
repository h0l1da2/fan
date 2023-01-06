package holiday.fan.domain.members;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberStop is a Querydsl query type for MemberStop
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberStop extends EntityPathBase<MemberStop> {

    private static final long serialVersionUID = 195759370L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberStop memberStop = new QMemberStop("memberStop");

    public final QAdmin admin;

    public final StringPath cause = createString("cause");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QMemberStop(String variable) {
        this(MemberStop.class, forVariable(variable), INITS);
    }

    public QMemberStop(Path<? extends MemberStop> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberStop(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberStop(PathMetadata metadata, PathInits inits) {
        this(MemberStop.class, metadata, inits);
    }

    public QMemberStop(Class<? extends MemberStop> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new QAdmin(forProperty("admin"), inits.get("admin")) : null;
    }

}

