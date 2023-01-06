package holiday.fan.domain.mdmarket;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.ConstructorExpression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.Expression;


/**
 * QMd is a Querydsl query type for Md
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMd extends EntityPathBase<Md> {

    private static final long serialVersionUID = 1559185369L;

    public static ConstructorExpression<Md> create(Expression<String> name) {
        return Projections.constructor(Md.class, new Class<?>[]{String.class}, name);
    }

    public static final QMd md = new QMd("md");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    public QMd(String variable) {
        super(Md.class, forVariable(variable));
    }

    public QMd(Path<? extends Md> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMd(PathMetadata metadata) {
        super(Md.class, metadata);
    }

}

