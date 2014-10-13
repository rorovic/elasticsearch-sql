/*
 * Copyright 1999-2011 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.durid.sql.dialect.mysql.ast.expr;

import org.durid.sql.ast.expr.SQLCharExpr;
import org.durid.sql.dialect.mysql.visitor.MySqlASTVisitor;
import org.durid.sql.visitor.SQLASTVisitor;

public class MySqlCharExpr extends SQLCharExpr implements MySqlExpr {

    private static final long serialVersionUID = 1L;

    private String            charset;
    private String            collate;

    public MySqlCharExpr(){

    }

    public MySqlCharExpr(String text){
        super(text);
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getCollate() {
        return collate;
    }

    public void setCollate(String collate) {
        this.collate = collate;
    }

    public void output(StringBuffer buf) {
        if (charset != null) {
            buf.append(charset);
            buf.append(' ');
        }

        super.output(buf);

        if (collate != null) {
            buf.append(" COLLATE ");
            buf.append(collate);
        }
    }
    
    @Override
    protected void accept0(SQLASTVisitor visitor) {
        if (visitor instanceof MySqlASTVisitor) {
            accept0((MySqlASTVisitor) visitor);
        } else {
            visitor.visit(this);
            visitor.endVisit(this);
        }
    }

    public void accept0(MySqlASTVisitor visitor) {
        visitor.visit(this);
        visitor.endVisit(this);
    }
}