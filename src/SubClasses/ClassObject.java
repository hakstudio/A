package SubClasses;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

public class ClassObject extends SimpleJavaFileObject {
    public String classs = "";
    public String code = "";

    public ClassObject(String classs, String code) {
        super(URI.create("files:///" + classs + Kind.SOURCE.extension), Kind.SOURCE);
        this.classs = classs;
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }
}
