package twitter4j;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import twitter4j.conf.Configuration;

final class QueryResultJSONImpl extends TwitterResponseImpl implements Serializable, QueryResult {
    private static final long serialVersionUID = -5359566235429947156L;
    private double completedIn;
    private int count;
    private long maxId;
    private String nextResults;
    private String query;
    private String refreshUrl;
    private long sinceId;
    private List<Status> tweets;

    QueryResultJSONImpl(HttpResponse httpResponse, Configuration configuration) {
        super(httpResponse);
        JSONObject asJSONObject = httpResponse.asJSONObject();
        try {
            JSONObject jSONObject = asJSONObject.getJSONObject("search_metadata");
            this.completedIn = ParseUtil.getDouble("completed_in", jSONObject);
            this.count = ParseUtil.getInt("count", jSONObject);
            this.maxId = ParseUtil.getLong("max_id", jSONObject);
            this.nextResults = jSONObject.has("next_results") ? jSONObject.getString("next_results") : null;
            this.query = ParseUtil.getURLDecodedString("query", jSONObject);
            this.refreshUrl = ParseUtil.getUnescapedString("refresh_url", jSONObject);
            this.sinceId = ParseUtil.getLong("since_id", jSONObject);
            JSONArray jSONArray = asJSONObject.getJSONArray("statuses");
            this.tweets = new ArrayList(jSONArray.length());
            if (configuration.isJSONStoreEnabled()) {
                TwitterObjectFactory.clearThreadLocalMap();
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                this.tweets.add(new StatusJSONImpl(jSONArray.getJSONObject(i), configuration));
            }
        } catch (Throwable e) {
            throw new TwitterException(e.getMessage() + ":" + asJSONObject.toString(), e);
        }
    }

    QueryResultJSONImpl(Query query) {
        this.sinceId = query.getSinceId();
        this.count = query.getCount();
        this.tweets = new ArrayList(0);
    }

    public long getSinceId() {
        return this.sinceId;
    }

    public long getMaxId() {
        return this.maxId;
    }

    public String getRefreshURL() {
        return this.refreshUrl;
    }

    public int getCount() {
        return this.count;
    }

    public double getCompletedIn() {
        return this.completedIn;
    }

    public String getQuery() {
        return this.query;
    }

    public List<Status> getTweets() {
        return this.tweets;
    }

    public Query nextQuery() {
        if (this.nextResults == null) {
            return null;
        }
        return Query.createWithNextPageQuery(this.nextResults);
    }

    public boolean hasNext() {
        return this.nextResults != null;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        QueryResult queryResult = (QueryResult) obj;
        if (Double.compare(queryResult.getCompletedIn(), this.completedIn) != 0) {
            return false;
        }
        if (this.maxId != queryResult.getMaxId()) {
            return false;
        }
        if (this.count != queryResult.getCount()) {
            return false;
        }
        if (this.sinceId != queryResult.getSinceId()) {
            return false;
        }
        if (!this.query.equals(queryResult.getQuery())) {
            return false;
        }
        if (this.refreshUrl == null ? queryResult.getRefreshURL() != null : !this.refreshUrl.equals(queryResult.getRefreshURL())) {
            return false;
        }
        if (this.tweets != null) {
            if (this.tweets.equals(queryResult.getTweets())) {
                return true;
            }
        } else if (queryResult.getTweets() == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((this.refreshUrl != null ? this.refreshUrl.hashCode() : 0) + (((((int) (this.sinceId ^ (this.sinceId >>> 32))) * 31) + ((int) (this.maxId ^ (this.maxId >>> 32)))) * 31)) * 31) + this.count;
        long doubleToLongBits = this.completedIn != 0.0d ? Double.doubleToLongBits(this.completedIn) : 0;
        hashCode = ((((hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + this.query.hashCode()) * 31;
        if (this.tweets != null) {
            i = this.tweets.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "QueryResultJSONImpl{sinceId=" + this.sinceId + ", maxId=" + this.maxId + ", refreshUrl='" + this.refreshUrl + '\'' + ", count=" + this.count + ", completedIn=" + this.completedIn + ", query='" + this.query + '\'' + ", tweets=" + this.tweets + '}';
    }
}