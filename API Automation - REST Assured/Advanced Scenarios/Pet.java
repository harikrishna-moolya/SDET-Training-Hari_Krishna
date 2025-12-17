package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {

    public int id;
    public Category category;
    public String name;
    public List<String> photoUrls;
    public String status;
}
