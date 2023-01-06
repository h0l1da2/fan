package holiday.fan.domain.consulting;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConsult is a Querydsl query type for Consult
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConsult extends EntityPathBase<Consult> {

    private static final long serialVersionUID = -74711667L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConsult consult = new QConsult("consult");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final holiday.fan.domain.members.QMember member;

    public final DateTimePath<java.util.Date> modifyDate = createDateTime("modifyDate", java.util.Date.class);

    public final QConsultRemover remover;

    public final DateTimePath<java.util.Date> reservationDate = createDateTime("reservationDate", java.util.Date.class);

    public final EnumPath<ConsultStatus> status = createEnum("status", ConsultStatus.class);

    public final DateTimePath<java.time.LocalDateTime> updateDate = createDateTime("updateDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> uploadDate = createDateTime("uploadDate", java.time.LocalDateTime.class);

    public QConsult(String variable) {
        this(Consult.class, forVariable(variable), INITS);
    }

    public QConsult(Path<? extends Consult> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConsult(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConsult(PathMetadata metadata, PathInits inits) {
        this(Consult.class, metadata, inits);
    }

    public QConsult(Class<? extends Consult> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new holiday.fan.domain.members.QMember(forProperty("member"), inits.get("member")) : null;
        this.remover = inits.isInitialized("remover") ? new QConsultRemover(forProperty("remover"), inits.get("remover")) : null;
    }

}

