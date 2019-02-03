import java.util.Map;

public interface ITemplate {
    public Field create_Fields(String filename, Map<String, String> fields_map);

    public Field add_field(String name, String value);
}
