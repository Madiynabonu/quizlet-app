package com.nooglers.services.userprogress;

import com.nooglers.dao.UserProgressDao;
import com.nooglers.domains.progress.UserProgress;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserProgressService {
    private static final ThreadLocal<UserProgressService> USER_PROGRESS_SERVICE_THREAD_LOCAL =
            ThreadLocal.withInitial(UserProgressService::new);
    UserProgressDao dao = UserProgressDao.getInstance();


    public List<UserProgress> getUserProgress(Integer id, Integer moduleId) {
        return dao.findAll(id, moduleId);
    }

    public static UserProgressService getInstance() {
        return USER_PROGRESS_SERVICE_THREAD_LOCAL.get();
    }
}
