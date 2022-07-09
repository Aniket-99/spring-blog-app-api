package com.api.blog.services;

import java.util.List;

import com.api.blog.payloads.CategoryDto;

public interface CategoryService {

	public CategoryDto createCategory(CategoryDto categoryDto);

	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);

	public void deleteCategory(int categoryId);

	public CategoryDto getSingleCategory(int categoryId);

	public List<CategoryDto> getAllCategory();
}
