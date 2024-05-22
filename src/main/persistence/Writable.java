package persistence;

import org.json.JSONObject;

/***
 * Citation:
 *      Title: JsonSerializationDemo
 *      Date: 27 October 2023
 *      Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
 */
public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}

