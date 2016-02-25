package pl.java.scalatech.web.hateoas;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;

import pl.java.scalatech.web.images.dto.Photo;

class PhotoResourceProcessor implements ResourceProcessor<Resource<Photo>> {
        @Override
        public Resource<Photo> process(Resource<Photo> resource) {
            Photo photo = resource.getContent();
            //String key = photo.getKey();
           // String id = photo.getId();
            //resource.add(new Link(ServletUriComponentsBuilder.fromCurrentRequest()
              //      .path("/doges/{key}/photos/{id}/photo").buildAndExpand(key, id).toString(), "photo"));

            return resource;
        }
    }