package com.example.networkwen;

import java.util.List;

public class AndroidKnowledgeResponse {
    private String id;
    private String object;
    private long created;
    private String result;
    private boolean need_clear_history;
    private Usage usage;

    public class Usage{
        private int prompt_tokens;
        private int completion_tokens;
        private int total_tokens;

        public void setCompletion_tokens(int completion_tokens) {
            this.completion_tokens = completion_tokens;
        }

        public void setPrompt_tokens(int prompt_tokens) {
            this.prompt_tokens = prompt_tokens;
        }

        public void setTotal_tokens(int total_tokens) {
            this.total_tokens = total_tokens;
        }

        public int getCompletion_tokens() {
            return completion_tokens;
        }

        public int getPrompt_tokens() {
            return prompt_tokens;
        }

        public int getTotal_tokens() {
            return total_tokens;
        }
    }

    public void setCreated(long created) {
        this.created = created;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setNeed_clear_history(boolean need_clear_history) {
        this.need_clear_history = need_clear_history;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public String getId() {
        return id;
    }

    public Usage getUsage() {
        return usage;
    }

    public long getCreated() {
        return created;
    }

    public String getObject() {
        return object;
    }

    public String getResult() {
        return result;
    }

    public boolean getNeed_clear_history() {
        return need_clear_history;
    }

}
