package holiday.fan.domain.members;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberLog is a Querydsl query type for MemberLog
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberLog extends EntityPathBase<MemberLog> {

    private static final long serialVersionUID = -824976068L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberLog memberLog = new QMemberLog("memberLog");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> joinDate = createDateTime("joinDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> lastLogin = createDateTime("lastLogin", java.time.LocalDateTime.class);

    public final EnumPath<MailConfirm> mailConfirm = createEnum("mailConfirm", MailConfirm.class);

    public final QMember member;

    public final QMemberStop memberStop;

    public final DateTimePath<java.time.LocalDateTime> removeDate = createDateTime("removeDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public QMemberLog(String variable) {
        this(MemberLog.class, forVariable(variable), INITS);
    }

    public QMemberLog(Path<? extends MemberLog> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberLog(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberLog(PathMetadata metadata, PathInits inits) {
        this(MemberLog.class, metadata, inits);
    }

    public QMemberLog(Class<? extends MemberLog> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.memberStop = inits.isInitialized("memberStop") ? new QMemberStop(forProperty("memberStop"), inits.get("memberStop")) : null;
    }

}

