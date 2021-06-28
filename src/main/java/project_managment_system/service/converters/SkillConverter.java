package project_managment_system.service.converters;

import project_managment_system.dao.entity.SkillDao;
import project_managment_system.dto.SkillTo;

import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SkillConverter {
    public static SkillDao toSkillDao(SkillTo skillTo) {
        SkillDao skillDao = new SkillDao();
        skillDao.setIdSkill(skillTo.getIdSkill());
        skillDao.setLanguage(skillTo.getLanguage());
        skillDao.setLevel(skillTo.getLevel());
        skillDao.setDevelopers(skillTo.getDevelopers());
        return skillDao;
    }

    public static SkillTo fromSkillDao(SkillDao skillDao) {
        SkillTo skillTo = new SkillTo();
        skillTo.setIdSkill(skillDao.getIdSkill());
        skillTo.setLanguage(skillDao.getLanguage());
        skillTo.setLevel(skillDao.getLevel());
        skillTo.setDevelopers(skillDao.getDevelopers());
        return skillTo;
    }

    public static Set<SkillTo> allFromSkillDao(Set<SkillDao> skills) {
        return skills.stream()
                .map(SkillConverter::fromSkillDao)
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
