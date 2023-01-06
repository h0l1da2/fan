package holiday.fan.domain.fanletter;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoardMember is a Querydsl query type for BoardMember
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoardMember extends EntityPathBase<BoardMember> {

    private static final long serialVersionUID = 618890674L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoardMember boardMember = new QBoardMember("boardMember");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final holiday.fan.domain.members.QMember member;

    public QBoardMember(String variable) {
        this(BoardMember.class, forVariable(variable), INITS);
    }

    public QBoardMember(Path<? extends BoardMember> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoardMember(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoardMember(PathMetadata metadata, PathInits inits) {
        this(BoardMember.class, metadata, inits);
    }

    public QBoardMember(Class<? extends BoardMember> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new holiday.fan.domain.members.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

