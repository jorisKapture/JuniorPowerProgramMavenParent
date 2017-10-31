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

  private handleError(error: any): Promise<any>{
    console.error('error: ' + error.message, error);
    return Promise.reject(error.message || error);
  }
}
