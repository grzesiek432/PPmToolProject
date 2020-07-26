package com.chojnacki.grzegorz.ppmtool.services;

import com.chojnacki.grzegorz.ppmtool.domain.Project;

public interface ProjectService {

    Project saveOrUpdate(Project project);
}
