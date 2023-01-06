package holiday.fan.domain.place;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlace is a Querydsl query type for Place
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlace extends EntityPathBase<Place> {

    private static final long serialVersionUID = -2122145753L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlace place = new QPlace("place");

    public final holiday.fan.domain.members.QAdmin admin;

    public final StringPath content = createString("content");

    public final holiday.fan.domain.file.QFileInfo fileInfo;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMap map;

    public final QPlaceRemover placeRemover;

    public final EnumPath<Star> star = createEnum("star", Star.class);

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> uploadDate = createDateTime("uploadDate", java.time.LocalDateTime.class);

    public QPlace(String variable) {
        this(Place.class, forVariable(variable), INITS);
    }

    public QPlace(Path<? extends Place> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlace(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlace(PathMetadata metadata, PathInits inits) {
        this(Place.class, metadata, inits);
    }

    public QPlace(Class<? extends Place> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new holiday.fan.domain.members.QAdmin(forProperty("admin"), inits.get("admin")) : null;
        this.fileInfo = inits.isInitialized("fileInfo") ? new holiday.fan.domain.file.QFileInfo(forProperty("fileInfo"), inits.get("fileInfo")) : null;
        this.map = inits.isInitialized("map") ? new QMap(forProperty("map"), inits.get("map")) : null;
        this.placeRemover = inits.isInitialized("placeRemover") ? new QPlaceRemover(forProperty("placeRemover"), inits.get("placeRemover")) : null;
    }

}

