package be.kapture.quizinator.root.mapper;

import org.dozer.DozerBeanMapper;

import java.util.ArrayList;
import java.util.List;

public class DozerBeanMultimapper {
    private DozerBeanMapper dozerBeanMapper;

    public <T> List<T> mapCollection(List<?> src, final Class<T> targetClass) {
        List<T> result = new ArrayList<T>();
        src.forEach(s -> result.add(dozerBeanMapper.map(s, targetClass)));
        return result;
    }
}
