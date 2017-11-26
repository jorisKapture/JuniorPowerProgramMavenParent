import {Injectable} from "@angular/core";
import{Headers, Http} from "@angular/http";
import {Tag} from "../model/tag";

import {observable} from "rxjs/symbol/observable";
import "rxjs/operator/catch";
import "rxjs/operator/map";
import "rxjs/Rx";
import 'rxjs/add/operator/toPromise';

@Injectable()
export class TagService{
  private baseUrl = "http://localhost:8080/tag";
  constructor(private http: Http){}

  getTags(): Promise<Tag[]>{
    return this.http.get(this.baseUrl)
      .toPromise()
      .then(response => response.json() as Tag[])
      .catch(this.handleError);
  }

  deleteTag(id : number) : Promise<void>{
    return this.http.delete(this.baseUrl + "/" + id)
      .toPromise()
      .then(()=>null)
      .catch(this.handleError);
  }

  saveTag(tag) : Promise<Tag>{
    return this.http.post(this.baseUrl + "/create", tag)
      .toPromise()
      .then(res => res.json().data as Tag)
      .catch(this.handleError);
  }

  findCreateTags(tagsString : String) : Promise<Tag[]>{
    if(!!!tagsString){
      console.log("test1");
      return Promise.resolve([] as Tag[]);
    }
    return this.http.post(this.baseUrl + "/findcreate", tagsString)
      .toPromise()
      .then(res => res.json() as Tag[])
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any>{
    return Promise.reject(error.message || error);
  }
}
