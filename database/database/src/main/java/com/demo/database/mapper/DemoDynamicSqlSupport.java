package mapper;

import java.sql.JDBCType;
import org.mybatis.dynamic.sql.AliasableSqlTable;
import org.mybatis.dynamic.sql.SqlColumn;

public final class DemoDynamicSqlSupport {
    public static final Demo demo = new Demo();

    public static final SqlColumn<Integer> id = demo.id;

    public static final SqlColumn<String> message = demo.message;

    public static final class Demo extends AliasableSqlTable<Demo> {
        public final SqlColumn<Integer> id = column("id", JDBCType.INTEGER);

        public final SqlColumn<String> message = column("message", JDBCType.VARCHAR);

        public Demo() {
            super("demo", Demo::new);
        }
    }
}