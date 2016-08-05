package com.hascode.tutorial;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.bytes.BytesReference;
import org.elasticsearch.index.query.QueryBuilders;

public class Main {

	public static void main(String[] args) {
		final BytesReference byteRef = QueryBuilders.termsQuery("", "").buildAsBytes();
		SearchResponse r;
	}

}
