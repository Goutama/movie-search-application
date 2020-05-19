import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {SearchPageInput} from "./search-page-input.model";

@Injectable({
  providedIn: 'root'
})
export class SearchPageService {

  constructor(private http: HttpClient) { }

  get(searchInput): Observable<any> {
    return this.http.get(`/api/movies/_search`, {
      params: {
        firstName: searchInput.firstName,
        secondName: searchInput.secondName
      },
      observe: 'response'
    })
      .pipe(
        catchError(SearchPageService.formatErrors)
      );
  }

  private static formatErrors(error: any) {
    return  throwError(error.error);
  }
}
