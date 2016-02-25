package pl.java.scalatech.service.bean;

import java.util.List;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ServerFolder implements Comparable<ServerFolder> {

    private @NonNull final String caption;
    private @NonNull final String id;
    private @NonNull final String path;
    private  final int position = 0;
    private @NonNull final List<String> users = null;
    private long sizeInBytes;


    @Override
    public int compareTo(ServerFolder o) {
        if (position < o.getPosition()) {
            return -1;
        } else if (position == o.getPosition()) {
            return 0;
        } else {
            return 1;
        }
    }

}