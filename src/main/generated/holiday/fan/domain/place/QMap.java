package holiday.fan.domain.place;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMap is a Querydsl query type for Map
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMap extends EntityPathBase<Map> {

    private static final long serialVersionUID = 1378792572L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMap map = new QMap("map");

    public final holiday.fan.domain.mdmarket.QAddress address;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> x = createNumber("x", Integer.class);

    public final NumberPath<Integer> y = createNumber("y", Integer.class);

    public QMap(String variable) {
        this(Map.class, forVariable(variable), INITS);
    }

    public QMap(Path<? extends Map> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMap(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMap(PathMetadata metadata, PathInits inits) {
        this(Map.class, metadata, inits);
    }

    public QMap(Class<? extends Map> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.address = inits.isInitialized("address") ? new holiday.fan.domain.mdmarket.QAddress(forProperty("address")) : null;
    }

}

