package holiday.fan.domain.consulting;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QConsultRemover is a Querydsl query type for ConsultRemover
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QConsultRemover extends EntityPathBase<ConsultRemover> {

    private static final long serialVersionUID = 1723845153L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QConsultRemover consultRemover = new QConsultRemover("consultRemover");

    public final holiday.fan.domain.members.QAdmin admin;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final holiday.fan.domain.members.QMember member;

    public final DateTimePath<java.time.LocalDateTime> removeDate = createDateTime("removeDate", java.time.LocalDateTime.class);

    public QConsultRemover(String variable) {
        this(ConsultRemover.class, forVariable(variable), INITS);
    }

    public QConsultRemover(Path<? extends ConsultRemover> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QConsultRemover(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QConsultRemover(PathMetadata metadata, PathInits inits) {
        this(ConsultRemover.class, metadata, inits);
    }

    public QConsultRemover(Class<? extends ConsultRemover> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.admin = inits.isInitialized("admin") ? new holiday.fan.domain.members.QAdmin(forProperty("admin"), inits.get("admin")) : null;
        this.member = inits.isInitialized("member") ? new holiday.fan.domain.members.QMember(forProperty("member"), inits.get("member")) : null;
    }

}

