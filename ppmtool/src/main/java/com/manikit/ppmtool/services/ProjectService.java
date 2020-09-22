package com.manikit.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manikit.ppmtool.demo.Project;
import com.manikit.ppmtool.exceptions.ProjectIdException;
import com.manikit.ppmtool.repositories.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	
	public Project saveOrUpdateProject(Project project) {
		
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

			return projectRepository.save(project);
			
		}catch(Exception e) {
			throw new ProjectIdException("Project ID: "+
		project.getProjectIdentifier().toUpperCase()+ "already exists");
		}
	}
	
	public Project findProjectByIdentifier(String projectId) {
		
		Project project = projectRepository.findByProjectIdentifier(projectId);
		if(project == null) {
			throw new ProjectIdException("Project ID:" + projectId + "does not exists");
		}
		return project;
				
	}
}
