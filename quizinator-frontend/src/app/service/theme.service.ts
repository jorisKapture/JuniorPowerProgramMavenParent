import {Injectable} from "@angular/core";
import{Headers, Http} from "@angular/http";
import {Theme} from "../model/theme";

import {observable} from "rxjs/symbol/observable";
import "rxjs/operator/catch";
import "rxjs/operator/map";
import "rxjs/Rx";
import 'rxjs/add/operator/toPromise';

@Injectable()
export class ThemeService{
  private baseUrl = "http://localhost:8080/theme";
  constructor(private http: Http){}

  getThemes(): Promise<Theme[]>{
    return this.http.get(this.baseUrl)
      .toPromise()
      .then(response => response.json() as Theme[])
      .catch(this.handleError);
  }

  deleteTheme(id : number) : Promise<void>{
    return this.http.delete(this.baseUrl + "/" + id)
      .toPromise()
      .then(()=>null)
      .catch(this.handleError);
  }

  saveTheme(theme) : Promise<Theme>{
    return this.http.post(this.baseUrl + "/create", theme)
      .toPromise()
      .then(res => res.json().data as Theme)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any>{
    return Promise.reject(error.message || error);
  }
}
