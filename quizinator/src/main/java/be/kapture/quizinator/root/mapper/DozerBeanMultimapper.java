package be.kapture.quizinator.root.mapper;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DozerBeanMultimapper {
    private DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();

    public <T> List<T> mapCollection(List<?> src, final Class<T> targetClass) {
        List<T> result = new ArrayList<T>();
        src.forEach(s -> result.add(dozerBeanMapper.map(s, targetClass)));
        return result;
    }
}
