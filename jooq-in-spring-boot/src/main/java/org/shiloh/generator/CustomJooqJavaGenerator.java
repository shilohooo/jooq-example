package org.shiloh.generator;

import org.jooq.codegen.JavaGenerator;
import org.jooq.codegen.JavaWriter;
import org.jooq.meta.TableDefinition;
import org.jooq.tools.JooqLogger;
import org.shiloh.extend.dao.impl.BaseDaoImpl;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;

import static org.jooq.codegen.GeneratorStrategy.Mode.DAO;

/**
 * 自定义 jOOQ 代码生成器
 *
 * @author shiloh
 * @date 2023/2/18 11:23
 */
public class CustomJooqJavaGenerator extends JavaGenerator {
    private static final JooqLogger LOGGER = JooqLogger.getLogger(CustomJooqJavaGenerator.class);

    private static final String JOOQ_DAO_IMPL_IMPORT = "import org.jooq.impl.DAOImpl;\n";
    private static final String OLD_EXTEND_DAO = "extends DAOImpl";
    private static final String NEW_EXTEND_DAO = "extends BaseDaoImpl";

    /**
     * 重写 DAO 类生成逻辑，让表对应的 DAO 类继承自定义的 {@link BaseDaoImpl}
     *
     * @param table 表定义对象
     * @author shiloh
     * @date 2023/2/18 11:26
     */
    @Override
    protected void generateDao(TableDefinition table) {
        super.generateDao(table);
        final File file = super.getFile(table, DAO);
        if (!file.exists()) {
            return;
        }

        try {
            String fileContent = new String(FileCopyUtils.copyToByteArray(file));
            // 替换继承的类
            fileContent = fileContent.replace(JOOQ_DAO_IMPL_IMPORT, "");
            fileContent = fileContent.replace(OLD_EXTEND_DAO, NEW_EXTEND_DAO);
            FileCopyUtils.copy(fileContent.getBytes(), file);
        } catch (IOException e) {
            LOGGER.error("jOOQ 生成 DAO 类失败：", e);
        }
    }

    @Override
    protected void generateDao(TableDefinition table, JavaWriter out) {
        // 用于生成 import org.shiloh.extend.dao.impl.BaseDaoImpl; 内容
        out.ref(BaseDaoImpl.class);
        super.generateDao(table, out);
    }
}
