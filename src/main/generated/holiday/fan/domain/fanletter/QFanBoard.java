package holiday.fan.domain.fanletter;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFanBoard is a Querydsl query type for FanBoard
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFanBoard extends EntityPathBase<FanBoard> {

    private static final long serialVersionUID = 662543425L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFanBoard fanBoard = new QFanBoard("fanBoard");

    public final QBoardMember boardMember;

    public final QBoardRemover boardRemover;

    public final StringPath content = createString("content");

    public final holiday.fan.domain.file.QFileInfo fileInfo;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> uploadDate = createDateTime("uploadDate", java.time.LocalDateTime.class);

    public QFanBoard(String variable) {
        this(FanBoard.class, forVariable(variable), INITS);
    }

    public QFanBoard(Path<? extends FanBoard> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFanBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFanBoard(PathMetadata metadata, PathInits inits) {
        this(FanBoard.class, metadata, inits);
    }

    public QFanBoard(Class<? extends FanBoard> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.boardMember = inits.isInitialized("boardMember") ? new QBoardMember(forProperty("boardMember"), inits.get("boardMember")) : null;
        this.boardRemover = inits.isInitialized("boardRemover") ? new QBoardRemover(forProperty("boardRemover"), inits.get("boardRemover")) : null;
        this.fileInfo = inits.isInitialized("fileInfo") ? new holiday.fan.domain.file.QFileInfo(forProperty("fileInfo"), inits.get("fileInfo")) : null;
    }

}

