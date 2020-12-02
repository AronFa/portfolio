package eu.itswc.persistance;

import eu.itswc.model.MediaDescriptor;
import org.springframework.data.repository.CrudRepository;

public interface MediaDescriptorRepository extends CrudRepository<MediaDescriptor, String> {
    MediaDescriptor findOneById(String descriptorId);
}
