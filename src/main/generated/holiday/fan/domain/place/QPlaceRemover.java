package holiday.fan.domain.place;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPlaceRemover is a Querydsl query type for PlaceRemover
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPlaceRemover extends EntityPathBase<PlaceRemover> {

    private static final long serialVersionUID = 1541662279L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPlaceRemover placeRemover = new QPlaceRemover("placeRemover");

    public final holiday.fan.domain.members.QAdmin admin;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> removeDate = createDateTime("removeDate", java.time.LocalDateTime.class);

    public QPlaceRemover(String variable) {
        this(PlaceRemover.class, forVariable(variable), INITS);
    }

    public QPlaceRemover(Path<? extends PlaceRemover> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPlaceRemover(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPlaceRemover(PathMetadata metadata, PathInits inits) {
        this(PlaceRemover.class, metadata, inits);
    }

    public QPlaceRemover(Class<? extends PlaceRemover> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new holiday.fan.domain.members.QAdmin(forProperty("admin"), inits.get("admin")) : null;
    }

}

