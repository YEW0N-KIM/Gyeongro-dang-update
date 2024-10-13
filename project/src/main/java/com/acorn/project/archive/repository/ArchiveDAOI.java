package com.acorn.project.archive.repository;

import com.acorn.project.archive.domain.Archive;

public interface ArchiveDAOI {

	int insert(Archive archive);

	int delete(Archive archive);

	Archive selectOne(Archive archive);

}