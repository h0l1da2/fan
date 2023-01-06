package holiday.fan.domain.fanletter;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentRemover is a Querydsl query type for CommentRemover
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentRemover extends EntityPathBase<CommentRemover> {

    private static final long serialVersionUID = -1219353795L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentRemover commentRemover = new QCommentRemover("commentRemover");

    public final holiday.fan.domain.members.QAdmin admin;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final holiday.fan.domain.members.QMember member;

    public final DateTimePath<java.time.LocalDateTime> removeDate = createDateTime("removeDate", java.time.LocalDateTime.class);

    public QCommentRemover(String variable) {
        this(CommentRemover.class, forVariable(variable), INITS);
    }

    public QCommentRemover(Path<? extends CommentRemover> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommentRemover(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommentRemover(PathMetadata metadata, PathInits inits) {
        this(CommentRemover.class, metadata, inits);
    }

    public QCommentRemover(Class<? extends CommentRemover> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new holiday.fan.domain.members.QAdmin(forProperty("admin"), inits.get("admin")) : null;
        this.member = inits.isInitialized("member") ? new holiday.fan.domain.members.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

