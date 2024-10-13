package com.acorn.project.archive.service;

import com.acorn.project.archive.domain.Archive;

public interface ArchiveServiceI {

	int insert(Archive archive);

	int delete(Archive archive);

	Archive checkArch(Archive archive);

}