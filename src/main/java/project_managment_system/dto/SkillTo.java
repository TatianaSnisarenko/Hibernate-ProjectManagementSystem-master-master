package project_managment_system.dto;

import project_managment_system.dao.entity.DeveloperDao;

import java.util.Objects;
import java.util.Set;

public class SkillTo implements Comparable<SkillTo>{
    private int idSkill;
    private String language;
    private String level;
    private Set<DeveloperDao> developers;

    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int idSkill) {
        this.idSkill = idSkill;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Set<DeveloperDao> getDevelopers() {
        return developers;
    }

    public void setDevelopers(Set<DeveloperDao> developers) {
        this.developers = developers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkillTo skillTo = (SkillTo) o;
        return Objects.equals(language, skillTo.language) && Objects.equals(level, skillTo.level);
    }

    @Override
    public int hashCode() {
        return Objects.hash(language, level);
    }

    @Override
    public int compareTo(SkillTo skillTo) {
        return Integer.compare(this.getIdSkill(), skillTo.getIdSkill());
    }
}
