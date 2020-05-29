package com.tsilva.springFood.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by Telmo Silva on 15.05.2020.
 */

@Entity
@Table(name = "recipe_search")
public class RecipeSearch implements Serializable
{
	private static final long serialVersionUID = 42865132230005L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "search_query")
	private String searchQuery;

	@Column(name = "update_time_stamp")
	private Long updateTimeStamp;

	@Column(name = "successful_iteration")
	private Boolean successfulIteration;

	@Column(name = "offset")
	private Long offset;

	public RecipeSearch() {}

	public RecipeSearch(String searchQuery, Long updateTimeStamp, Boolean successfulIteration)
	{
		this.searchQuery = searchQuery;
		this.updateTimeStamp = updateTimeStamp;
		this.successfulIteration = successfulIteration;
		this.offset = 0L;
	}

	public Long getId()
	{
		return id;
	}

	public String getSearchQuery()
	{
		return searchQuery;
	}

	public Long getUpdateTimeStamp()
	{
		return updateTimeStamp;
	}

	public Boolean getSuccessfulIteration()
	{
		return successfulIteration;
	}

	public Long getOffset()
	{
		return offset;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public void setSearchQuery(String searchQuery)
	{
		this.searchQuery = searchQuery;
	}

	public void setUpdateTimeStamp(Long updateTimeStamp)
	{
		this.updateTimeStamp = updateTimeStamp;
	}

	public void setSuccessfulIteration(Boolean successfulIteration)
	{
		this.successfulIteration = successfulIteration;
	}

	public void setOffset(Long offset)
	{
		this.offset = offset;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		RecipeSearch that = (RecipeSearch) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(searchQuery, that.searchQuery) &&
				Objects.equals(updateTimeStamp, that.updateTimeStamp) &&
				Objects.equals(successfulIteration, that.successfulIteration) &&
				Objects.equals(offset, that.offset);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, searchQuery, updateTimeStamp, successfulIteration, offset);
	}

	@Override
	public String toString()
	{
		return "RecipeSearch{" +
				"id=" + id +
				", searchQuery='" + searchQuery + '\'' +
				", updateTimeStamp=" + updateTimeStamp +
				", successfulIteration=" + successfulIteration +
				", offset=" + offset +
				'}';
	}
}
