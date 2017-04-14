package com.ilya.art.repositories.utils;

public enum QueryHelper {

	ENTITY_TABLE(":entity_table"), ENTITY_COLUMN_SEARCH_IN(":entity_column_search_in"), ENTITY_COLUMN_SEARCH_VALUE(
			":entity_column_search_value");

	private final String text;

	private QueryHelper(final String text) {
		this.text = text;
	}

	@Override
	public final String toString() {
		return text;
	}

	static public final  String FIND_ENTITY_BY_ONE_FIELD_EQUALITY_NAMED_QUERY = "SELECT FROM :entity_table WHERE :entity_column_search_in"
			+ " = :entity_column_search_value";

}
