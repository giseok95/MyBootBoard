package kr.gi.mybootboard.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = 2146827388L;

    public static final QMember member = new QMember("member1");

    public final ListPath<kr.gi.mybootboard.board.entity.Board, kr.gi.mybootboard.board.entity.QBoard> boardList = this.<kr.gi.mybootboard.board.entity.Board, kr.gi.mybootboard.board.entity.QBoard>createList("boardList", kr.gi.mybootboard.board.entity.Board.class, kr.gi.mybootboard.board.entity.QBoard.class, PathInits.DIRECT2);

    public final BooleanPath enabled = createBoolean("enabled");

    public final StringPath id = createString("id");

    public final StringPath name = createString("name");

    public final StringPath password = createString("password");

    public final EnumPath<kr.gi.mybootboard.domain.Role> role = createEnum("role", kr.gi.mybootboard.domain.Role.class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

