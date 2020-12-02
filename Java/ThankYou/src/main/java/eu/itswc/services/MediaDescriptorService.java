package eu.itswc.services;

import eu.itswc.model.MediaDescriptor;
import eu.itswc.persistance.MediaDescriptorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaDescriptorService {
    
    private MediaDescriptorRepository mediaDescriptorRepository;

    public MediaDescriptorService(@Autowired MediaDescriptorRepository mediaDescriptorRepository) {
        this.mediaDescriptorRepository = mediaDescriptorRepository;
    }
    
    public Iterable<MediaDescriptor> getAllMediaDesciptors() {
        return mediaDescriptorRepository.findAll();
    }

    public MediaDescriptor getMediaDescriptorById(String descriptorId) {
        return mediaDescriptorRepository.findOneById(descriptorId);
    }
    
    public void addMediaDesrciptor(MediaDescriptor mediaDescriptor){
        mediaDescriptorRepository.save(mediaDescriptor);
    }
    
    public void updateMediaDescriptor(MediaDescriptor mediaDescriptor) {
        mediaDescriptorRepository.save(mediaDescriptor);
    }
    
    public void deleteMediaDesciptorById(String descriptorId) {
        mediaDescriptorRepository.deleteById(descriptorId);
    }

}
