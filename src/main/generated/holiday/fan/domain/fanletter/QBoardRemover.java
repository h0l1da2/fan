package holiday.fan.domain.fanletter;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardRemover is a Querydsl query type for BoardRemover
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardRemover extends EntityPathBase<BoardRemover> {

    private static final long serialVersionUID = -2146271146L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardRemover boardRemover = new QBoardRemover("boardRemover");

    public final holiday.fan.domain.members.QAdmin admin;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final holiday.fan.domain.members.QMember member;

    public final DateTimePath<java.time.LocalDateTime> removeDate = createDateTime("removeDate", java.time.LocalDateTime.class);

    public QBoardRemover(String variable) {
        this(BoardRemover.class, forVariable(variable), INITS);
    }

    public QBoardRemover(Path<? extends BoardRemover> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardRemover(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardRemover(PathMetadata metadata, PathInits inits) {
        this(BoardRemover.class, metadata, inits);
    }

    public QBoardRemover(Class<? extends BoardRemover> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new holiday.fan.domain.members.QAdmin(forProperty("admin"), inits.get("admin")) : null;
        this.member = inits.isInitialized("member") ? new holiday.fan.domain.members.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

