package com.nooglers.services;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FolderService {
    private static final ThreadLocal<FolderService> FOLDER_SERVICE_THREAD_LOCAL = ThreadLocal.withInitial(FolderService::new);

    public static FolderService getInstance() {
        return FOLDER_SERVICE_THREAD_LOCAL.get();
    }
}
