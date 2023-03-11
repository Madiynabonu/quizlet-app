package com.nooglers.services;

import com.nooglers.dao.ClassDao;
import com.nooglers.dao.ModuleDao;
import com.nooglers.domains.Class;
import com.nooglers.domains.Module;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@NoArgsConstructor( access = AccessLevel.PRIVATE )
public class ClassService {
    private static final ThreadLocal<ClassService> CLASS_SERVICE_THREAD_LOCAL = ThreadLocal.withInitial(ClassService::new);

    private final ClassDao dao = ClassDao.getInstance();
    private final ModuleDao moduleDao = ModuleDao.getInstance();

    public Class getGroup(Integer groupId) {
        return dao.findById(groupId);
    }

    public Set<Module> getGroupModules(Integer groupId , Integer userId) {
        return moduleDao.getGroupModules(groupId , userId);
    }

    public boolean addClassModule(Integer groupId , Integer moduleId) {
        return dao.addClassModule(groupId , moduleId);
    }

    public Set<Module> getModules(Integer groupId) {
        return dao.getGroupModules(groupId);
    }

    public void remove(Integer moduleId , Integer groupId) {
        dao.removeModule(moduleId , groupId);
    }

    public static ClassService getInstance() {
        return CLASS_SERVICE_THREAD_LOCAL.get();
    }

    public boolean addUser(Integer userId , Integer groupId) {

        return dao.addMemberToGroup(userId,groupId);
    }
}
