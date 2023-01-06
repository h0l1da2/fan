package holiday.fan.domain.members;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberRemove is a Querydsl query type for MemberRemove
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberRemove extends EntityPathBase<MemberRemove> {

    private static final long serialVersionUID = -896345204L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberRemove memberRemove = new QMemberRemove("memberRemove");

    public final QAdmin admin;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final DateTimePath<java.time.LocalDateTime> removeDate = createDateTime("removeDate", java.time.LocalDateTime.class);

    public QMemberRemove(String variable) {
        this(MemberRemove.class, forVariable(variable), INITS);
    }

    public QMemberRemove(Path<? extends MemberRemove> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberRemove(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberRemove(PathMetadata metadata, PathInits inits) {
        this(MemberRemove.class, metadata, inits);
    }

    public QMemberRemove(Class<? extends MemberRemove> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new QAdmin(forProperty("admin"), inits.get("admin")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
    }

}

