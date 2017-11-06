import {Injectable} from "@angular/core";
import{Headers, Http} from "@angular/http";
import {Question} from "../model/question";

import {observable} from "rxjs/symbol/observable";
import "rxjs/operator/catch";
import "rxjs/operator/map";
import "rxjs/Rx";
import 'rxjs/add/operator/toPromise';

@Injectable()
export class QuestionService{
  private baseUrl = "http://localhost:8080/question";
  constructor(private http: Http){}

  getQuestions(): Promise<Question[]>{
    return this.http.get(this.baseUrl)
      .toPromise()
      .then(response => response.json() as Question[])
      .catch(this.handleError);
  }

  deleteQuestion(id : number) : Promise<void>{
    return this.http.delete(this.baseUrl + "/" + id)
      .toPromise()
      .then(()=>null)
      .catch(this.handleError);
  }

  saveQuestion(question) : Promise<Question>{
    return this.http.post(this.baseUrl + "/create", question)
      .toPromise()
      .then(res => res.json().data as Question)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any>{
    return Promise.reject(error.message || error);
  }
}
