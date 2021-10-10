package me.plich.empik.github;

interface HttpDataFetcher {
    <T> T makeGetRequest(String url, Class<T> responseType);
}
