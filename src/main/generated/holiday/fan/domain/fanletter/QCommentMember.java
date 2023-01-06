package holiday.fan.domain.fanletter;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCommentMember is a Querydsl query type for CommentMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCommentMember extends EntityPathBase<CommentMember> {

    private static final long serialVersionUID = 2034264555L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCommentMember commentMember = new QCommentMember("commentMember");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final holiday.fan.domain.members.QMember member;

    public QCommentMember(String variable) {
        this(CommentMember.class, forVariable(variable), INITS);
    }

    public QCommentMember(Path<? extends CommentMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCommentMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCommentMember(PathMetadata metadata, PathInits inits) {
        this(CommentMember.class, metadata, inits);
    }

    public QCommentMember(Class<? extends CommentMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new holiday.fan.domain.members.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

